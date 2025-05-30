package nl.openminetopia.modules.skript.expressions.fitness;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import nl.openminetopia.api.player.PlayerManager;
import nl.openminetopia.api.player.objects.MinetopiaPlayer;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

public class ExprKilometersFlown extends SimpleExpression<Integer> {

    static {
        Skript.registerExpression(ExprKilometersFlown.class, Integer.class, ExpressionType.COMBINED, "[the] (omt|openminetopia) fitness kilometers flown of %player%");
    }

    private Expression<Player> player;

    @Override
    public Class<? extends Integer> getReturnType() {
        return Integer.class;
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        player = (Expression<Player>) exprs[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "Example expression with expression player" + player.toString(event, debug);
    }

    @Override
    @Nullable
    protected Integer[] get(Event event) {
        Player p = player.getSingle(event);
        MinetopiaPlayer minetopiaPlayer = PlayerManager.getInstance().getOnlineMinetopiaPlayer(p);
        if (minetopiaPlayer == null) return null;
        return new Integer[] {(minetopiaPlayer.getBukkit().getStatistic(Statistic.AVIATE_ONE_CM) / 1000)};

    }
}